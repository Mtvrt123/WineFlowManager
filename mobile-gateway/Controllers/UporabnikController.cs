using Microsoft.AspNetCore.Mvc;

namespace mobile_gateway.Controllers
{
    [ApiController]
    [Route("api/uporabnik")]
    public class UporabnikController : Controller
    {

        private readonly ILogger<UporabnikController> _logger;
        public UporabnikController(ILogger<UporabnikController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        [Route("heatcheck")]
        public IActionResult Heatcheck()
        {
            try
            {
                _logger.LogInformation("Heatcheck");
                string odgovor = Grpc.HealthCheck();
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("Heatcheck error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpGet]
        [Route("get/{id}")]
        public IActionResult GetUporabnik(string id)
        {
            try
            {
                _logger.LogInformation("GetUporabnik");
                var odgovor = Grpc.GetUporabniki(id);
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("GetUporabnik error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }
    }
}
