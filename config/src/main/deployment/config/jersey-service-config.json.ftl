{
    "service": {
        "message": {
            "local": "@{jersey.service.message.local}",
            "qe": "@{jersey.service.message.qe}",
            "prod": "@{jersey.service.message.prod}"
        },
        "aws": {
            "url": "@{app.db.url}",
            "driver": "@{app.db.driver}",
            "username": "@{app.db.username}",
            "password": "@{app.db.password}"
        }
    }
}