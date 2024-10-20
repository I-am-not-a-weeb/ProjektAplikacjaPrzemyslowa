rootProject.name = "ProjektAplikacjaPrzemyslowa"
include("src:main:api")
findProject(":src:main:api")?.name = "api"
