在pom.xml 文件中添加maven 依赖插件

    <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>

启动服务  

    Java -jar peer1-1.0-SNAPSHOT.jar --spring.profiles.active=peer8762 &

    Java -jar peer1-1.0-SNAPSHOT.jar --spring.profiles.active=peer8763 &
    
    查看端口 lsof -i :8762 杀掉进程 kill -9 {pid}

