using Microsoft.AspNetCore.Mvc;

namespace web_gateway.Controllers
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

        [HttpPost]
        [Route("add")]
        public IActionResult AddUporabnik([FromBody] UporabnikRequest uporabnik)
        {
            try
            {
                _logger.LogInformation("AddUporabnik");
                var odgovor = Grpc.AddUporabnik(uporabnik);
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("AddUporabnik error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpPut]
        [Route("update")]
        public IActionResult UpdateUporabnik([FromBody] UporabnikRequestUpdate uporabnik)
        {
            try
            {
                _logger.LogInformation("UpdateUporabnik");
                var odgovor = Grpc.UpdateUporabnik(uporabnik);
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("UpdateUporabnik error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }

        [HttpDelete]
        [Route("delete/{id}")]
        public IActionResult DeleteUporabnik(string id)
        {
            try
            {
                _logger.LogInformation("DeleteUporabnik");
                var odgovor = Grpc.DeleteUporabnik(id);
                return Ok(odgovor);
            }
            catch (Exception e)
            {
                _logger.LogError("DeleteUporabnik error: " + e.Message);
                return StatusCode(500, e.Message);
            }
        }
    }
}
