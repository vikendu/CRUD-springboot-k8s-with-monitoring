## Java SpringBoot app to do CRUD with a UI.
![Screenshot 2024-12-21 115133](https://github.com/user-attachments/assets/4d7dfcf1-4a1d-470f-afc5-d092b2f7faa0)

### Can run on a K8s cluster or just Docker Compose

### Uses Kafka to build an audit trace & Logging events
![image](https://github.com/user-attachments/assets/0f23b5b3-a429-4012-8a49-0b9b3c459ec3)


### Monitoring using Prometheus & Grafana
![Screenshot 2024-12-26 203330](https://github.com/user-attachments/assets/5942202b-b340-4688-a1e5-b8041c9342a8)

![Screenshot 2024-12-26 203424](https://github.com/user-attachments/assets/a56ebd44-9cb7-4514-a0fe-3d4fdc93c662)


## Build Options
manually build app using maven, start mysql & kafka(please look into the compose file for any other dependancy)  

## Simpler options to run
just `docker compose up`  

OR just `kubectl apply -f k8s/<everything>.yaml` to get it up & running in a cluster.
Note: if deploying on a k8s cluster make sure to have kafka setup(Strimzi is cool)[https://strimzi.io/quickstarts/]

If everything went right the app should run at `http://localhost:8080/users`
