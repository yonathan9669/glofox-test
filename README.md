# Booking Manager

This repository was created to develop, plan, and register the solution for the glofox test. The tasks were properly registered in the [Create Classes](https://github.com/yonathan9669/glofox-test/issues/3) and [Book a Class](https://github.com/yonathan9669/glofox-test/issues/5) issues.

## Instructions
The whole project is containerized on Docker, so in order to run it you need to have `Docker` & `docker-compose` installed.

#### Steps
- Copy the `.env.example` file as `.env` within the repository.
- Open your console and run the following command.
```sh
make build
```

That's it, just wait until everything is setup for you and they can be accessed by:
- `http://localhost:8000/`    -> FRONTEND
- `http://localhost:8000/gw/` -> GATEWAY

You could access Hasura console providing the value at the `HASURA_ADMIN_SECRET` variable in your `.env` file.

---

## Endpoints
The custom business logic to complete the tasks was developed in the backend service, all the process and details of the requirements were explained in their corresponding PR.

- Create Classes => https://github.com/yonathan9669/glofox-test/pull/4
- Book a Class => https://github.com/yonathan9669/glofox-test/pull/6

## Architecture
> The following diagram describes the created architecture

![Estructura y flujo](https://user-images.githubusercontent.com/897389/179416308-fd4a1b1c-a823-40f7-9f27-0f7c9b9a9e3d.jpg)

## Database
> The ERM describes precisly how

![imagen](https://user-images.githubusercontent.com/897389/179417409-515fe44c-fb67-4544-a61a-4e5dab117379.png)
