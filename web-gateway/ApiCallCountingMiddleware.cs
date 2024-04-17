namespace web_gateway
{
    public class ApiCallCountingMiddleware
    {
        private readonly RequestDelegate _next;

        public ApiCallCountingMiddleware(RequestDelegate next)
        {
            _next = next;
        }

        public async Task InvokeAsync(HttpContext context)
        {
            // Logic to determine the operation type

            var path = context.Request.Path.Value ?? string.Empty;
            var segments = path.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);
            var lastSegment = segments.LastOrDefault();
            var isNumericLastSegment = int.TryParse(lastSegment, out _);

            switch (context.Request.Method.ToUpperInvariant())
            {
                case "POST":
                    ApiCallCounter.IncrementCreate();
                    break;
                case "GET":
                    if (isNumericLastSegment)
                        ApiCallCounter.IncrementReadById();
                    else
                        ApiCallCounter.IncrementReadAll();
                    break;
                case "PUT":
                case "PATCH":
                    ApiCallCounter.IncrementUpdate();
                    break;
                case "DELETE":
                    ApiCallCounter.IncrementDelete();
                    break;
            }

            await _next(context);
        }
    }

}
