# Greetings

Hi! Congratulations on progressing to the next stage of our interview process, we are glad you are here and cannot wait to see what you can do. We want to give you an idea of the kind of things you might be expected to do as a Software Developer here at [Target Information Systems](https://www.targetis.co.uk/).

# Welcome to GradRole

Welcome to **GradRole** our exciting new app. We have used [JHipster](https://www.jhipster.tech/) to create this starting point for you. This application is a [Spring Boot](https://spring.io/projects/spring-boot) backend and a [Vue.js](https://vuejs.org/) frontend with a [PostgreSQL](https://www.postgresql.org/) Database.

The application has a concept of users and HTTP Session Authentication so there is a secure area of the site you can log into. If and when you get the app up and running on your local machine you will see that quite a bit of work has already been done for you but we need your help to add in few new changes.

# Quick Start
**You will need to be running Java version v12, Node v14 and PostgreSQL v9. Other versions may work but if you are getting errors check your versions match.**

*Installing and launching the application can be quite challenging, but don't lose heart. Consider taking note of any difficulties you encounter and the solutions you come up with. Sharing your experience in overcoming these obstacles can be valuable and interesting for us to hear.*

1.  Install [Java](https://adoptopenjdk.net/), [Git](https://git-scm.com/), [Node.js](https://nodejs.org/en/) and [PostgreSQL](https://www.postgresql.org/).
2.  Install [JHipster](https://www.jhipster.tech/) `npm install -g generator-jhipster@beta` (we are using the beta because it supports Vue.js out of the box)
3.  Create a [GitHub](https://github.com/) account if you don't already have one
4.  [Fork](https://docs.github.com/en/github/getting-started-with-github/fork-a-repo) the application
5.  Create a local clone of your fork
6.  Create a new role and database in PostgreSQL called `gradrole`
7.  Run your Spring Boot application using `./mvnw`
8.  In a separate terminal window start the frontend applications using `npm start`
9.  Finally we recommend opening the project in an IDE such as [IntelliJIDEA](https://www.jetbrains.com/idea/) - this [guide](https://www.jhipster.tech/configuring-ide-idea/#-configuring-intellij-idea) will help you configure it

If you are using Intellij IDEA then we recommend that you enable "hot-restart" with [Spring Boot devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-devtools). This is a must-have feature, as it makes your application updated on the fly as and when you make changes.

If all of the above has gone well and you can access your application in your local web browser then well done. Its not always easy to get started with a new application and often documentation isn't great. Sometimes it's not always clear and this is where your problem solving skills will come into play!

One of things you will be expected to do is get up and running with applications fast. We have many applications at Target and having the ability to set them up is important as it means we can focus on delivering value to our customers.

Now lets get to work!

# New Requirements

The client has requested a few changes to this application. Have a go at implementing the following requirements:

- First of all the client really doesn't like the "Welcome, Java Hipster!" can we change this to something more fitting?
- Can we also remove the section around "if you have any questions on JHipster".
- It would be nice if there was an easier way to login. Perhaps the "Sign In" link could be a button?
- Currently users within the system have a first name and a last name it would be good if users could also have a middle name and job role.
- Slightly tricker now we need to know data of birth for the users. Could we have the ability to update users date of birth?
- In a similar way we have users it would be good if we could have a concept of Student within the system. It would be really good if we could Create, Read Update Delete (CRUD) Students. We need to know the name, email, dob, course they are studying.

Have a go at the above challenges. Try using git to commit your code and push back into your GitHub repository. When you are happy open a [pull request](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests) so we can review your code. Prefix your pull request with your name so we know who it is from.

# Extras

If you are wanting to delve a little deeper why not try adding some of your own features or ideas or try the following additional extras, again submitting any code changes as a pull request.

- Write a test
- Try using Docker and Docker compose to build an image of your application and run it inside a container
- Try installing [minikube](https://minikube.sigs.k8s.io/docs/start/) locally and deploy to your local Kubernetes cluster. See [Deploying to Kubernetes](https://www.jhipster.tech/kubernetes/) for more information.

If you have any questions don't hesitate to ask. The README continues below with other useful information.

Thanks for applying and Good Luck!

# gradrole

This application was generated using JHipster 7.0.0-beta.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1](https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1).

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

```
./mvnw
npm start
```

Npm is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `npm update` and `npm install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `npm help update`.

The `npm run` command will list all of the scripts available to run for this project.

### PWA Support

JHipster ships with PWA (Progressive Web App) support, and it's turned off by default. One of the main components of a PWA is a service worker.

The service worker initialization code is commented out by default. To enable it, uncomment the following code in `src/main/webapp/index.html`:

```html
<script>
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./service-worker.js').then(function () {
      console.log('Service Worker Registered');
    });
  }
</script>
```

Note: [Workbox](https://developers.google.com/web/tools/workbox/) powers JHipster's service worker. It dynamically generates the `service-worker.js` file.

### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

```
npm install --save --save-exact leaflet
```

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

```
npm install --save-dev --save-exact @types/leaflet
```

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:
Note: There are still a few other things remaining to do for Leaflet that we won't detail here.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the gradrole application for production, run:

```
./mvnw -Pprod clean verify
```

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

```
java -jar target/*.jar
```

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pprod,war clean verify
```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

### Client tests

Unit tests are run by [Jest][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

```
npm test
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Note: we have turned off authentication in [src/main/docker/sonar.yml](src/main/docker/sonar.yml) for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/postgresql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
./mvnw -Pprod verify jib:dockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 7.0.0-beta.1 archive]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v7.0.0-beta.1/setting-up-ci/
[node.js]: https://nodejs.org/
[webpack]: https://webpack.github.io/
[angular cli]: https://cli.angular.io/
[browsersync]: https://www.browsersync.io/
[jest]: https://facebook.github.io/jest/
[jasmine]: https://jasmine.github.io/2.0/introduction.html
[protractor]: https://angular.github.io/protractor/
[leaflet]: https://leafletjs.com/
[definitelytyped]: https://definitelytyped.org/
