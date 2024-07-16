package server.example.server.service.mplementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.example.server.enumration.Status;
import server.example.server.model.Server;
import server.example.server.repo.ServeRepo;
import server.example.server.service.ServerService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServiceImpl implements ServerService {

    private  final ServeRepo serverRepo;



    @Override
    public Server create(Server server) {
        log.info("Saving new Server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png","server1.png","server1.png","server1.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }


    @Override
    public Collection<Server> list(int limit) {
        log.info("Server to fetsching all: {}");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();


    }

    @Override
    public Server get(Long id)
    {
        log.info("Server to fetsching by id: {}");
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Saving new Server: {}", server.getName());

        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id)
    {
        log.info("delete new Server: {}");

        serverRepo.deleteById(id);
        return  Boolean.TRUE;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Server to ping: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus((address.isReachable(1000) ? Status.SERVER_UP: Status.SERVER_DOWN));
serverRepo.save(server);
        return server;
    }
}
