package server.example.server.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.example.server.model.Response;
import server.example.server.service.mplementation.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private  final ServiceImpl serverService;


    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {

        return  ResponseEntity.ok(
                Response.builder()
                        .timestam(LocalDateTime.now())
                        .data(Map.of("server", serverService.list(30)))
                        .message("server retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
