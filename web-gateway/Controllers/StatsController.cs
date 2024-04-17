using Microsoft.AspNetCore.Mvc;

namespace web_gateway.Controllers
{
    public class StatsController : Controller
    {
        [HttpGet("GetStats")]
        public IActionResult GetApiCallCounts()
        {
            var counts = ApiCallCounter.GetCounts();
            return Ok(counts);
        }

    }
}
