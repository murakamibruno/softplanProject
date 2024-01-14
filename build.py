import os
import threading

threads = []


def build_backend_application(app):
    threads.append(app)
    print("Building application {}".format(app))
    os.system("cd {} && mvn clean install -DskipTests".format(app))
    print("Application {} finished building!".format(app))
    threads.remove(app)

def build_frontend_application(app):
    threads.append(app)
    print("Building application {}".format(app))
    os.system("cd {} && npm ci && npm run build".format(app))
    print("Application {} finished building!".format(app))
    threads.remove(app)


def docker_compose_up():
    print("Running containers!")
    os.popen("docker-compose up --build --force-recreate").read()
    print("Pipeline finished!")


def build_all_applications():
    print("Starting to build applications!")
    threading.Thread(target=build_frontend_application,
                     args={"softplanFront"}).start()
    threading.Thread(target=build_backend_application,
                     args={"softplanBack"}).start()


def remove_remaining_containers():
    print("Removing all containers.")
    os.system("docker-compose down")
    containers = os.popen('docker ps -aq').read().split('\n')
    containers.remove('')
    if len(containers) > 0:
        print("There are still {} containers created".format(containers))
        for container in containers:
            print("Stopping container {}".format(container))
            os.system("docker container stop {}".format(container))
        os.system("docker container prune -f")


if __name__ == "__main__":
    print("Pipeline started!")
    build_all_applications()
    while len(threads) > 0:
        pass
    remove_remaining_containers()
    threading.Thread(target=docker_compose_up).start()
