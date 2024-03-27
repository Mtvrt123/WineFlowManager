using Microsoft.AspNetCore.Mvc;

namespace web_gateway.Controllers
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

        [HttpPost]
        [Route("vinograd")]
        public IActionResult PostVinaVinograd([FromBody] VinogradPost vinograd)
        {
            try
            {
                _logger.LogInformation("PostVinograd");
                string odgovor = REST.PostVinograd(vinograd).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("PostVinograd error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpPut]
        [Route("vinograd/{id}")]
        public IActionResult PutVinograd(int id, [FromBody] VinogradPost vinograd)
        {
            try
            {
                _logger.LogInformation("PutVinograd");
                string odgovor = REST.PutVinograd(id, vinograd).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("PutVinograd error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpDelete]
        [Route("vinograd/{id}")]
        public IActionResult DeleteVinograd(int id)
        {
            try
            {
                _logger.LogInformation("DeleteVinograd");
                string odgovor = REST.DeleteVinograd(id).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("DeleteVinograd error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }
    }
}
