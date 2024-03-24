package ita.uporabniki;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GrpcServiceTest {
        private static UporabnikRequest uporabnikRequest;

        @BeforeAll
        public static void setup() {
            uporabnikRequest = UporabnikRequest.newBuilder()
                    .setIme("Test")
                    .setPriimek("Test")
                    .setEmail("Test")
                    .build();
        }

        @Test
        public void testDodajUporabnika() {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9001)
                    .usePlaintext()
                    .build();
            UporabnikiServiceGrpc.UporabnikiServiceBlockingStub stub = UporabnikiServiceGrpc.newBlockingStub(channel);
            UporabnikReply response = stub.dodajUporabnika(uporabnikRequest);

            assertEquals("Test", response.getIme());
            assertEquals("Test", response.getPriimek());
            assertEquals("Test", response.getEmail());

            channel.shutdown();
        }
}


