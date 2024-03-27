using Microsoft.AspNetCore.Mvc;

namespace mobile_gateway.Controllers
{
    [ApiController]
    [Route("api/")]
    public class VinaController : Controller
    {
        private readonly ILogger<VinaController> _logger;

        public VinaController(ILogger<VinaController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        [Route("vina")]
        public IActionResult GetVina()
        {
            try
            {
                _logger.LogInformation("GetVina");
                string odgovor = REST.GetVina().Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("GetVina error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpGet]
        [Route("vina/{id}")]
        public IActionResult GetVino(int id)
        {
            try
            {
                _logger.LogInformation("GetVino");
                string odgovor = REST.GetVino(id).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("GetVino error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }
    }
}
