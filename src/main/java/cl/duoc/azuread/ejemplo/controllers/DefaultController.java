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

	@Value("${services.productos.getAllProductos}")
	private String urlGetAllProductos;
	
	@Value("${services.productos.updateProducto}")
	private String urlUpdateProducto;

	@Value("${services.productos.createProducto}")
	private String urlCreateProducto;

	@Value("${services.productos.deleteProducto}")
	private String urlDeleteProducto;

	@Value("${services.bodegas.getAllBodegas}")
	private String urlGetAllBodegas;

	@Value("${services.bodegas.updateBodega}")
	private String urlUpdateBodega;

	@Value("${services.bodegas.createBodega}")
	private String urlCreateBodega;

	@Value("${services.bodegas.deleteBodega}")
	private String urlDeleteBodega;

	@GetMapping("/api/status")
	public ResponseEntity<Object> mostrarListadoUrls() {
		return ResponseEntity.ok(
			java.util.Map.of(
				"productos", java.util.Map.of(
					"[GET]getAllProductos", urlGetAllProductos,
					"[POST]updateProducto", urlUpdateProducto,
					"[POST]createProducto", urlCreateProducto,
					"[POST]deleteProducto", urlDeleteProducto
				),
				"bodegas", java.util.Map.of(
					"[GET]getAllBodegas", urlGetAllBodegas,
					"[POST]updateBodega", urlUpdateBodega,
					"[POST]createBodega", urlCreateBodega,
					"[POST]deleteBodega", urlDeleteBodega
				)
			)
		);
	}



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
	public String forwardUpdateProducto(@PathVariable String id, @RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlUpdateProducto.replace("{id}", id);
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/productos/deleteProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String forwardDeleteProducto(@PathVariable String id, @RequestBody(required = false) String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson == null ? "" : rawJson, headers);
		String url = urlDeleteProducto.replace("{id}", id);
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@GetMapping(value = "/api/bodegas/getAllBodegas", produces = MediaType.APPLICATION_JSON_VALUE)
	public String forwardGetAllBodegas() {
		String url = urlGetAllBodegas;
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/bodegas/createBodega", produces = MediaType.APPLICATION_JSON_VALUE)
	public String forwardCreateBodega(@RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlCreateBodega;
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/bodegas/deleteBodega/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String forwardDeleteBodega(@PathVariable String id, @RequestBody(required = false) String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson == null ? "" : rawJson, headers);
		String url = urlDeleteBodega.replace("{id}", id);
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

	@PostMapping(value = "/api/bodegas/updateBodega/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String forwardUpdateBodega(@PathVariable String id, @RequestBody String rawJson) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(rawJson, headers);
		String url = urlUpdateBodega.replace("{id}", id);
		System.out.println("Forwarding raw JSON to: " + url);
		String response = restTemplate.postForObject(url, request, String.class);
		System.out.println("Response from backend: " + response);
		return response;
	}

}

