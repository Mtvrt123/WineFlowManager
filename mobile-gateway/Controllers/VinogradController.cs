using Microsoft.AspNetCore.Mvc;

namespace mobile_gateway.Controllers
{
    [ApiController]
    [Route("api/")]
    public class VinogradController : Controller
    {
        private readonly ILogger<VinogradController> _logger;

        public VinogradController(ILogger<VinogradController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        [Route("vinogradi")]
        public IActionResult GetVinogradi()
        {
            try
            {
                _logger.LogInformation("GetVinogradi");
                string odgovor = REST.GetVinogradi().Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("GetVinogradi error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpGet]
        [Route("vinograd/{id}")]
        public IActionResult GetVinograd(int id)
        {
            try
            {
                _logger.LogInformation("GetVinograd");
                string odgovor = REST.GetVinograd(id).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("GetVinograd error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }
    }
}
