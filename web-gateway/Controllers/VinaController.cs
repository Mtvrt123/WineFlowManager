using Microsoft.AspNetCore.Mvc;

namespace web_gateway.Controllers
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

        [HttpPost]
        [Route("vina")]
        public IActionResult PostVino([FromBody] VinoPost vino)
        {
            try
            {
                _logger.LogInformation("PostVina");
                string odgovor = REST.PostVino(vino).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("PostVina error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpPut]
        [Route("vina/{id}")]
        public IActionResult PutVino(int id, [FromBody] VinoPost vino)
        {
            try
            {
                _logger.LogInformation("PutVina");
                string odgovor = REST.PutVino(id, vino).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("PutVina error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpDelete]
        [Route("vina/{id}")]
        public IActionResult DeleteVino(int id)
        {
            try
            {
                _logger.LogInformation("DeleteVina");
                string odgovor = REST.DeleteVino(id).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("DeleteVina error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }
    }
}
