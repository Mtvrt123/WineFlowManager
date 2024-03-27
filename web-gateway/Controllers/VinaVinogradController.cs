using Microsoft.AspNetCore.Mvc;

namespace web_gateway.Controllers
{
    [ApiController]
    [Route("api/")]
    public class VinaVinogradController : Controller
    {
        private readonly ILogger<VinaVinogradController> _logger;

        public VinaVinogradController(ILogger<VinaVinogradController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        [Route("vinogradVina/{id}")]
        public IActionResult PostVinaVinograd(int id)
        {
            try
            {
                _logger.LogInformation("PostVinaVinograd");
                string odgovor = REST.PostVinaVinograd(id).Result;
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("PostVinaVinograd error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }        
    }
}
