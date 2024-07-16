package server.example.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.example.server.model.Server;

public interface ServeRepo extends JpaRepository<Server, Long> {

  Server findByIpAddress(String ipAddress);
  Server findByName(String name);

}
