package cl.duoc.azuread.ejemplo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class DefaultController {
	
	private final RestTemplate restTemplate = new RestTemplate();

	// REST Endpoints
	@Value("${services.productos.getAllProductos}")
	private String urlGetAllProductos;
	
	@Value("${services.productos.createProducto}")
	private String urlCreateProducto;

	@Value("${services.productos.updateProducto}")
	private String urlUpdateProducto;

	@Value("${services.bodegas.getAllBodegas}")
	private String urlGetAllBodegas;

	@Value("${services.bodegas.createBodega}")
	private String urlCreateBodega;

	@Value("${services.bodegas.updateBodega}")
	private String urlUpdateBodega;

	// Producer Endpoints
	@Value("${services.producer.createProducto}")
	private String urlProducerCreateProducto;

	@Value("${services.producer.deleteBodega}")
	private String urlProducerDeleteBodega;

	// GraphQL Endpoints
	@Value("${services.graphql.bodegas}")
	private String urlGraphQLBodegas;

	@Value("${services.graphql.productos}")
	private String urlGraphQLProductos;

	@GetMapping("/api/status")
	public ResponseEntity<Object> mostrarListadoUrls() {
		return ResponseEntity.ok(
			java.util.Map.of(
				"productos", java.util.Map.of(
					"[GET]getAllProductos", urlGetAllProductos,
					"[POST]createProducto", urlCreateProducto,
					"[POST]updateProducto", urlUpdateProducto
				),
				"bodegas", java.util.Map.of(
					"[GET]getAllBodegas", urlGetAllBodegas,
					"[POST]createBodega", urlCreateBodega,
					"[POST]updateBodega", urlUpdateBodega
				),
				"producer", java.util.Map.of(
					"[POST]createProducto", urlProducerCreateProducto,
					"[POST]deleteBodega", urlProducerDeleteBodega
				),
				"graphql", java.util.Map.of(
					"[POST]bodegas", urlGraphQLBodegas,
					"[POST]productos", urlGraphQLProductos
				),
				"servicio", java.util.Map.ofEntries(
					java.util.Map.entry("[GET]/api/status", "/api/status"),
					java.util.Map.entry("[GET]/api/productos/getAllProductos", "/api/productos/getAllProductos"),
					java.util.Map.entry("[POST]/api/productos/createProducto", "/api/productos/createProducto"),
					java.util.Map.entry("[POST]/api/productos/updateProducto/{id}", "/api/productos/updateProducto/{id}"),
					java.util.Map.entry("[GET]/api/bodegas/getAllBodegas", "/api/bodegas/getAllBodegas"),
					java.util.Map.entry("[POST]/api/bodegas/createBodega", "/api/bodegas/createBodega"),
					java.util.Map.entry("[POST]/api/bodegas/updateBodega/{id}", "/api/bodegas/updateBodega/{id}"),
					java.util.Map.entry("[POST]/api/producer/createProducto", "/api/producer/createProducto"),
					java.util.Map.entry("[POST]/api/producer/deleteBodega/{id}", "/api/producer/deleteBodega/{id}"),
					java.util.Map.entry("[POST]/api/graphql/bodegas", "/api/graphql/bodegas"),
					java.util.Map.entry("[POST]/api/graphql/productos", "/api/graphql/productos")
				)
			)
		);
	}

	// REST ENDPOINTS

	@GetMapping(value = "/api/productos/getAllProductos", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllProductos() {
		String url = urlGetAllProductos;
		System.out.println("Llamando al backend: " + url);
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Respuesta del backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/productos/createProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public String createProducto(@RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlCreateProducto;
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/productos/updateProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateProducto(@PathVariable String id, @RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlUpdateProducto.replace("{id}", id);
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@GetMapping(value = "/api/bodegas/getAllBodegas", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllBodegas() {
		String url = urlGetAllBodegas;
		System.out.println("Forwarding to: " + url);
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/bodegas/createBodega", produces = MediaType.APPLICATION_JSON_VALUE)
	public String createBodega(@RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlCreateBodega;
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/bodegas/updateBodega/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateBodega(@PathVariable String id, @RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlUpdateBodega.replace("{id}", id);
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	// PRODUCER ENDPOINTS

	@PostMapping(value = "/api/producer/createProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public String producerCreateProducto(@RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlProducerCreateProducto;
		System.out.println("Forwarding raw JSON to producer: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from producer: " + response);
		return response;
	}

	@PostMapping(value = "/api/producer/deleteBodega/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String producerDeleteBodega(@PathVariable String id, @RequestBody(required = false) String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson == null ? "" : rawJson, headers);
		String url = urlProducerDeleteBodega.replace("{id}", id);
		System.out.println("Forwarding to producer: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from producer: " + response);
		return response;
	}

	// GRAPHQL ENDPOINTS

	@PostMapping(value = "/api/graphql/bodegas", produces = MediaType.APPLICATION_JSON_VALUE)
	public String graphQLBodegas(@RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlGraphQLBodegas;
		System.out.println("Forwarding GraphQL query to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from GraphQL: " + response);
		return response;
	}

	@PostMapping(value = "/api/graphql/productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public String graphQLProductos(@RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlGraphQLProductos;
		System.out.println("Forwarding GraphQL query to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from GraphQL: " + response);
		return response;
	}

}