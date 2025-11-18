package co.edu.unbosque.model.persistence;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import co.edu.unbosque.beans.model.ConsultaDTO;
import co.edu.unbosque.beans.model.DiagnosticoDTO;
import co.edu.unbosque.beans.model.EpsCargaMensualDTO;
import co.edu.unbosque.beans.model.EpsDTO;
import co.edu.unbosque.beans.model.ExamenDTO;
import co.edu.unbosque.beans.model.MedicamentoDTO;
import co.edu.unbosque.beans.model.MedicamentoPorEpsDTO;
import co.edu.unbosque.beans.model.MedicoDTO;
import co.edu.unbosque.beans.model.PacienteDTO;
import co.edu.unbosque.beans.model.PerfilClinicoDTO;
import co.edu.unbosque.beans.model.RolDTO;
import co.edu.unbosque.beans.model.TopEnfermedadesDTO;
import co.edu.unbosque.beans.model.TopMedicosDTO;
import co.edu.unbosque.beans.model.UsuarioDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;

public class ExternalHTTPRequestHandler {

	private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();

	public static String convertirAJson(UsuarioDTO usuario) {
		String json = new GsonBuilder().create().toJson(usuario);
		return json;
	}

	public static String convertirAJson2(Integer id) {
		String json = new GsonBuilder().create().toJson(id);
		return json;
	}

	public static String convertirAJson3(String username) {
		String json = new GsonBuilder().create().toJson(username);
		return json;
	}

	public static String doGetUsuario(String url, UsuarioDTO usuario) {

		String json = convertirAJson(usuario);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return cuerpo;
		}

		RolDTO rol = new GsonBuilder().create().fromJson(cuerpo, RolDTO.class);
		return rol.getNombreRol();
	}

	public static List<ConsultaDTO> doGetPacientes(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer) (json, type,
				context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE)).create();

		return Arrays.asList(gson.fromJson(cuerpo, ConsultaDTO[].class));

	}

	public static UsuarioDTO doGetUsuario(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		if (response.statusCode() != 200) {
			System.out.println("Error al obtener usuario: " + response.statusCode());
			return null;
		}

		String json = response.body();
		return new GsonBuilder().create().fromJson(json, UsuarioDTO.class);
	}

	public static MedicoDTO doGetMedico(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		if (response.statusCode() != 200) {
			System.out.println("Error al obtener usuario: " + response.statusCode());
			return null;
		}

		String json = response.body();
		MedicoDTO medico = new GsonBuilder().create().fromJson(json, MedicoDTO.class);
		return medico;
	}
	
	public static PacienteDTO doGetPaciente(String url) {

	    HttpRequest request = HttpRequest.newBuilder()
	            .GET()
	            .uri(URI.create(url))
	            .build();

	    HttpResponse<String> response = null;

	    try {
	        response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        return null;
	    }

	    if (response.statusCode() != 200) {
	        System.out.println("Error al obtener paciente: " + response.statusCode());
	        return null;
	    }

	    String json = response.body();
	    System.out.println("JSON recibido (PACIENTE): " + json);

	    // 🔧 GSON con soporte para LocalDate
	    Gson gson = new GsonBuilder()
	            .registerTypeAdapter(LocalDate.class,
	                (JsonDeserializer<LocalDate>) (jsonEl, type, ctx) ->
	                        LocalDate.parse(jsonEl.getAsString()))
	            .registerTypeAdapter(LocalDate.class,
	                (JsonSerializer<LocalDate>) (date, type, ctx) ->
	                        new JsonPrimitive(date.toString()))
	            .create();

	    try {
	        return gson.fromJson(json, PacienteDTO.class);
	    } catch (Exception e) {
	        System.out.println("❌ Error al deserializar PacienteDTO: " + e.getMessage());
	        return null;
	    }
	}

	public static EpsDTO doGetEps(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		if (response.statusCode() != 200) {
			System.out.println("Error al obtener usuario: " + response.statusCode());
			return null;
		}

		String json = response.body();
		EpsDTO eps = new GsonBuilder().create().fromJson(json, EpsDTO.class);
		return eps;
	}

	public static List<UsuarioDTO> doGetAllUsuarios(String url) {

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String json = response.body();
		return Arrays.asList(new GsonBuilder().create().fromJson(json, UsuarioDTO[].class));
	}

	public static List<PacienteDTO> doGetPacientesPorMedico(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer) (json, type,
				context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE)).create();

		return Arrays.asList(gson.fromJson(cuerpo, PacienteDTO[].class));

	}
	
	public static List<DiagnosticoDTO> doGetDiagnosticosPorPaciente(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}

		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, DiagnosticoDTO[].class));

	}
	
	public static List<MedicamentoDTO> doGetMedicamentosPorPaciente(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}

		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, MedicamentoDTO[].class));

	}
	
	public static List<EpsCargaMensualDTO> doGetEpsCargaMensual(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}
		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, EpsCargaMensualDTO[].class));

	}
	
	public static List<TopMedicosDTO> doGetTopMedicos(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}
		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, TopMedicosDTO[].class));

	}
	
	public static List<TopEnfermedadesDTO> doGetTopEnfermedades(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}
		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, TopEnfermedadesDTO[].class));

	}
	
	public static List<MedicamentoPorEpsDTO> doGetMedicamentoPorEps(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}
		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, MedicamentoPorEpsDTO[].class));

	}
	
	public static List<MedicoDTO> doGetMedicosPorEps(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}
		
		return Arrays.asList(new GsonBuilder().create().fromJson(cuerpo, MedicoDTO[].class));

	}
	
	public static PerfilClinicoDTO doGetPerfilClinico(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}
		PerfilClinicoDTO pc = new GsonBuilder().create().fromJson(cuerpo, PerfilClinicoDTO.class);
		return pc;
	}
	
	public static List<ExamenDTO> doGetExamenesPorPaciente(String url) {

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		HttpResponse<String> response = null;
		try {
			response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		String cuerpo = response.body();

		if (response.statusCode() != 200) {
			return null;
		}

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, (JsonDeserializer) (json, type,
				context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE)).create();

		return Arrays.asList(gson.fromJson(cuerpo, ExamenDTO[].class));
	}
	
	
	private static String prettyPrintUsingGson(String uglyJson) {
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		JsonElement jsonElement = JsonParser.parseString(uglyJson);
		String prettyJsonString = gson.toJson(jsonElement);
		return prettyJsonString;
	}

}
