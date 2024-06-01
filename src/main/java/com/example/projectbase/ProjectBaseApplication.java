package com.example.projectbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Slf4j
//@RequiredArgsConstructor
//@EnableConfigurationProperties({AdminInfoProperties.class})
@SpringBootApplication
public class ProjectBaseApplication {

//  private final UserRepository userRepository;
//
//  private final RoleRepository roleRepository;
//
//  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
//    Environment env = SpringApplication.run(ProjectBaseApplication.class, args).getEnvironment();
//    String appName = env.getProperty("spring.application.name");
//    if (appName != null) {
//      appName = appName.toUpperCase();
//    }
//    String port = env.getProperty("server.port");
//    log.info("-------------------------START " + appName
//        + " Application------------------------------");
//    log.info("   Application         : " + appName);
//    log.info("   Url swagger-ui      : http://localhost:" + port + "/swagger-ui.html");
//    log.info("-------------------------START SUCCESS " + appName
//        + " Application------------------------------");
    SpringApplication.run(ProjectBaseApplication.class, args);
  }

//  @Bean
//  CommandLineRunner init(AdminInfoProperties userInfo) {
//    return args -> {
//      //init role
//      if (roleRepository.count() == 0) {
//        roleRepository.save(new Role(null, RoleConstant.ADMIN, null));
//        roleRepository.save(new Role(null, RoleConstant.USER, null));
//      }
//      //init admin
//      if (userRepository.count() == 0) {
//        User admin = User.builder().username(userInfo.getUsername())
//            .password(passwordEncoder.encode(userInfo.getPassword()))
//            .fullName(userInfo.getFullName()).email(userInfo.getEmail()).defaultLanguage(userInfo.getDefaultLanguage())
//            .timezone(userInfo.getTimeZone())
//            .role(roleRepository.findByRoleName(RoleConstant.ADMIN)).build();
//        userRepository.save(admin);
//      }
//    };
//  }

}
