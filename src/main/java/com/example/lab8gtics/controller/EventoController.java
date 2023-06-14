package com.example.lab8gtics.controller;



import com.example.lab8gtics.entity.Evento;
import com.example.lab8gtics.repository.EventoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EventoController {


    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }


    @GetMapping("/evento")
    public List<Evento> listarEventos(){

        return eventoRepository.findAll();
    }

    @GetMapping("/evento/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerEventoPorId(
            @PathVariable("id") String idStr) {

        HashMap<String,Object> responseJson = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<Evento> optEvent = eventoRepository.findById(id);
            if (optEvent.isPresent()) {
                responseJson.put("evento",optEvent.get());
                responseJson.put("resultado","exitoso");
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("msg","Evento no encontrado");
            }
        } catch (NumberFormatException e) {
            responseJson.put("msg","el ID debe ser un número entero positivo");
        }
        responseJson.put("resultado","Falla");
        return ResponseEntity.badRequest().body(responseJson);

    }



    @PostMapping("/evento")
    public ResponseEntity<HashMap<String, Object>> crearEvento(
            @RequestBody Evento evento,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();
        Evento eventoRegistrado;
        eventoRepository.save(evento);
        responseJson.put("estado","creado");
        if(fetchId) {
            List<Evento> eventos = eventoRepository.findAll();
            for (Evento e : eventos) {
                if(e.getNombre().equals(evento.getNombre()) && e.getDescripcion().equals(evento.getDescripcion())) {
                    eventoRegistrado = e;
                    responseJson.put("id", eventoRegistrado.getId());
                    break;
                }
            }

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request){
        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseMap.put("msg","Debe enviar un evento");
            responseMap.put("estado","error");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }








}
