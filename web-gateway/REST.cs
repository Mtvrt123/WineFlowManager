using System.Text;
using System.Text.Json;

namespace web_gateway
{
    public class REST
    {
        public static string url_vino = Environment.GetEnvironmentVariable("vino_url"); //"http://localhost:8180/api/v1/";
        public static string url_vinograd = Environment.GetEnvironmentVariable("vinograd_url"); //"http://localhost:8082/";

        public static async Task<string> GetVina()
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url_vino + "vina");
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> GetVinogradi()
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url_vinograd + "vinograd");
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> GetVino(int id)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url_vino + "vina/" + id);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> GetVinograd(int id)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url_vinograd + "vinograd/" + id);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> PostVinaVinograd(int id)
        {
            string vinograd = await GetVinograd(id);
            var vinogradJson = JsonSerializer.Deserialize<Vinograd>(vinograd);

            string vina = await GetVina();
            var vinaJson = JsonSerializer.Deserialize<List<Vino>>(vina);


            VinaVinograd vinaVinograd = new VinaVinograd();
            vinaVinograd.vinograd = vinogradJson;
            vinaVinograd.vina = vinaJson;

            return JsonSerializer.Serialize(vinaVinograd);
        }

        public static async Task<string> PostVinograd(VinogradPost vinograd)
        {
            using var client = new HttpClient();
            var json = JsonSerializer.Serialize(vinograd);
            var data = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url_vinograd + "vinograd", data);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> PostVino(VinoPost vino)
        {
            using var client = new HttpClient();
            var json = JsonSerializer.Serialize(vino);
            var data = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url_vino + "vina", data);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> PutVinograd(int id, VinogradPost vinograd)
        {
            using var client = new HttpClient();
            var json = JsonSerializer.Serialize(vinograd);
            var data = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PutAsync(url_vinograd + "vinograd/" + id, data);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> PutVino(int id, VinoPost vino)
        {
            using var client = new HttpClient();
            var json = JsonSerializer.Serialize(vino);
            var data = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PutAsync(url_vino + "vina/" + id, data);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }   

        public static async Task<string> DeleteVinograd(int id)
        {
            using var client = new HttpClient();
            var response = await client.DeleteAsync(url_vinograd + "vinograd/" + id);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }

        public static async Task<string> DeleteVino(int id)
        {
            using var client = new HttpClient();
            var response = await client.DeleteAsync(url_vino + "vina/" + id);
            var responseString = response.Content.ReadAsStringAsync().Result;
            return responseString;
        }
    }

    public class VinaVinograd
    {
        public Vinograd vinograd { get; set; }
        public List<Vino> vina { get; set; }

    }

    public class VinogradPost
    {
        public string naziv { get; set; }
        public float povrsina { get; set; }
        public string letozacetka { get; set; }
    }

    public class VinoPost
    {
        public string naziv { get; set; }
        public string sorta { get; set; }
        public string proizvajalec { get; set; }
        public string drzava { get; set; }
        public string regija { get; set; }
        public string opis { get; set; }
    }

    public class Vinograd
    {
        public int id { get; set; }
        public string naziv { get; set; }
        public float povrsina { get; set; }
        public string letozacetka { get; set; }
    }

    public class Vino
    {
        public int id { get; set; }
        public string naziv { get; set; }
        public string sorta { get; set; }
        public string proizvajalec { get; set; }
        public string drzava { get; set; }
        public string regija { get; set; }
        public string opis { get; set; }
    }


}
