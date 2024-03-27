using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;

namespace web_gateway
{
    public class Grpc
    {
        private static string url = Environment.GetEnvironmentVariable("uporabnik_url"); // "http://localhost:9000";

        public static string HealthCheck()
        {
            using var channel = GrpcChannel.ForAddress(url);
            var client = new UporabnikiService.UporabnikiServiceClient(channel);
            
            var reply = client.HealthCheck(new HealthCheckRequest(), deadline: DateTime.UtcNow.AddSeconds(5));
            Console.WriteLine("Health check: " + reply.Status);
            
            return reply.Status;
        }

        public static UporabnikReply GetUporabniki(string id)
        {
            using var channel = GrpcChannel.ForAddress(url);
            var client = new UporabnikiService.UporabnikiServiceClient(channel);
            
            var reply = client.VrniUporabnika(new UporabnikRequestID { Id = id }, deadline: DateTime.UtcNow.AddSeconds(5));
            Console.WriteLine("Uporabniki: " + reply.Id + " " + reply.Ime + " " + reply.Priimek);

            return reply;
        }
        
        public static UporabnikReply AddUporabnik(UporabnikRequest uporabnik)
        {
            using var channel = GrpcChannel.ForAddress(url);
            var client = new UporabnikiService.UporabnikiServiceClient(channel);
            
            var reply = client.DodajUporabnika(uporabnik, deadline: DateTime.UtcNow.AddSeconds(5));
            Console.WriteLine("Uporabniki: " + reply.Id + " " + reply.Ime + " " + reply.Priimek);

            return reply;
        }

        public static UporabnikReply UpdateUporabnik(UporabnikRequestUpdate uporabnikRequestUpdate)
        {
            using var channel = GrpcChannel.ForAddress(url);
            var client = new UporabnikiService.UporabnikiServiceClient(channel);
            
            var reply = client.PosodobiUporabnika(uporabnikRequestUpdate, deadline: DateTime.UtcNow.AddSeconds(5));
            Console.WriteLine("Uporabniki: " + reply.Id + " " + reply.Ime + " " + reply.Priimek);

            return reply;
        }

        public static UporabnikReply DeleteUporabnik(string id)
        {
            using var channel = GrpcChannel.ForAddress(url);
            var client = new UporabnikiService.UporabnikiServiceClient(channel);
            
            var reply = client.IzbrisiUporabnika(new UporabnikRequestID { Id = id }, deadline: DateTime.UtcNow.AddSeconds(5));
            Console.WriteLine("Uporabniki: " + reply.Id + " " + reply.Ime + " " + reply.Priimek);

            return reply;
        }
    }
}
