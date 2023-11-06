# ktor-hello-world

based on this [Udemy Course](https://www.udemy.com/course/build-modern-android-app-with-rest-api-and-ktor-server/) Section 2


## Topics Covered
- Embedded Server vs. Engine Main
- Multiple Modules
- Running locally on port 8080 
- Passing URL Params 
- Passing Query Parameters
- Returning Custom Object
- Try / Catch with various error codes
- Redirecting to another URL
- Serving static content -> text, html, jpg
- HTML DSL 


## Project Setup
- Configured using IntelliJ's Ktor Configurator vs. following the course.
- [Ktor Project Generator](https://start.ktor.io/#/settings?name=ktor-sample&website=example.com&artifact=com.example.ktor-sample&kotlinVersion=1.9.20&ktorVersion=2.3.5&buildSystem=GRADLE_KTS&engine=NETTY&configurationIn=CODE&addSampleCode=true&plugins=) Is also very useful to configure and create the project with all necessary plugins / resources. 


## Configs
Referenced this [Config Example](https://github.com/ktorio/ktor-documentation/blob/2.3.5/codeSnippets/snippets/json-kotlinx/build.gradle.kts) as a model for the build.gradle


## Plugin Integration 
Followed [Ktor Docs](https://ktor.io/docs/serialization.html) for Content Negotiation and Serialization.
- Add Dependencies: Content Negotiation + Json
- Install Content Negotiation
- Configure a Serializaer -> Json 


## Static Content 
Course is using deprecated calls for serving static content. I implemented what was recommended in the [Ktor Docs](https://ktor.io/docs/serving-static-content.html#folders)

