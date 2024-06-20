package com.alura.desafioconversionmonedas.principal;

import com.alura.desafioconversionmonedas.modelos.ConversionExchangeRate;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner tipoDato = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("********************************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("********************************************");

            opcion = tipoDato.nextInt();

            String monedaOrigen = "";
            String monedaDestino = "";

            if (opcion == 1) {
                monedaOrigen = "USD";
                monedaDestino = "ARS";
                System.out.println("Conversión de Dólar a Peso argentino seleccionada.");
                // Aquí iría la lógica para la conversión
            } else if (opcion == 2) {
                monedaOrigen = "ARS";
                monedaDestino = "USD";
                System.out.println("Conversión de Peso argentino a Dólar seleccionada.");
                // Aquí iría la lógica para la conversión
            } else if (opcion == 3) {
                monedaOrigen = "USD";
                monedaDestino = "BRL";
                System.out.println("Conversión de Dólar a Real brasileño seleccionada.");
                // Aquí iría la lógica para la conversión
            } else if (opcion == 4) {
                monedaOrigen = "BRL";
                monedaDestino = "USD";
                System.out.println("Conversión de Real brasileño a Dólar seleccionada.");
                // Aquí iría la lógica para la conversión
            } else if (opcion == 5) {
                monedaOrigen = "USD";
                monedaDestino = "COP";
                System.out.println("Conversión de Dólar a Peso colombiano seleccionada.");
                // Aquí iría la lógica para la conversión
            } else if (opcion == 6) {
                monedaOrigen = "COP";
                monedaDestino = "USD";
                System.out.println("Conversión de Peso colombiano a Dólar seleccionada.");
                // Aquí iría la lógica para la conversión
            } else if (opcion == 7) {
                System.out.println("Saliendo del conversor de moneda. ¡Hasta luego!");
            } else {
                System.out.println("Opción no válida. Por favor, elija una opción del 1 al 7.");
            }

            if (opcion >= 1 && opcion <= 6) {
                int valor;
                Scanner moneda = new Scanner(System.in);
                System.out.println("Ingrese el valor que desea convertir: ");
                valor = moneda.nextInt();

                String direccion = "https://v6.exchangerate-api.com/v6/2d4e05f3861e2be04ba0676f/pair/" + monedaOrigen + "/" + monedaDestino + "/" + valor;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String conversion = response.body();
                Gson gson = new Gson();
                ConversionExchangeRate respuestaConversion = gson.fromJson(conversion,ConversionExchangeRate.class);

                System.out.println("El valor "+valor+ " ["+monedaOrigen+"] "+ " corresponde al valor final de =>>> "+respuestaConversion.getConversionResult() + " ["+monedaDestino+"] ");
            }


        }

    }

}
