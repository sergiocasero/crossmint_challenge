# Crossmint challenge

## How to run the code

Run the code is quite easy, but you'll need to have IntelliJ Idea IDE

1. Open the project with IntelliJ
2. Go to src/main/phase1.kt and click on "play button" to execute the first phase
3. Go to src/main/phase2.kt and click on "play button"  to execute the second phase

## Phase 2 overview

The solution is quite simple, I'm just getting the goal, mapping it and then pushing the data to the endpoint

### Architecture

Since this is a simple project, I haven't considered to introduce a complex architecture, but at least I've created a
remote datasource (who manages the requests calls), mappers and models.

### Folders

- datasource: It contains the remote datasource, with it's implementation
- mapper: Contains extensions functions to map the dto data from/to domain
- model: Contains domain and data transfer object models, plus Success

## FAQ

### Why phase 1 is implemented with a different approach?

Just to demonstrate algorithm skills, I've decided to write the first phase with a simple algorithm with O(n*m)
complexity, where n is the row number and m the cols one

### Why the "delay" in the function?

Well, the API sometimes was answering with "429 too many requests" but I guess this is not the goal of the challenge, it
could be improved by just saving the requests that fails and then retry, or save them in a database to post them later

### Why do I use Kotlin.Result?

It's to demonstrate I know we should handle the IO operations by a way that able us to handle exceptions. In the project
the exceptions are not being handled, I'm just printing the result, in a prod environment, this must be improved

In fact, a better approach is to use some special class like an Either, because all our exceptions must be well-known,
so if an exceptions happens in a datasouce, we must parse and convert it to an specific domain-error in order to display
the error in the UI layer (if exists)

### Why mappers are extension functions?

Extension functions are a so helpful "feature" that kotlin provides, this is interesting because we don't need to create
a class with just one function, I think this approach is better

### Why a sealed class?

Sealed classes are so interested since they are like a "powerful enum", which means we could have lot of advantages,
like if we want to check the final instance, it's exhaustive. We can solve this with inheritance but IMHO this approach
is better since we must cover all the cases (like in the `when` inside `phase2.kt` file)

### Why ktor?

Ktor is not only a powerful http client but also a simple one, and it's more or less the standard in Kotlin & kotlin
multiplatform projects.

### Is this code multiplatform?

Yes, if we just configure the project correctly, this code can be run in iOS and Android devices.

### Why the mappers are being called in the datasource?

Because it's his responsibility, the models (from a domain perspective) should be the domain ones

### Why ignoring the body result in the remote datasource?

Well, because it's a coding challenge, I'm only checking if the request fail, this must be improved in a prod
environment

### Why gradle as build system?

Is the Android Standard

### Why the candidate ID is hardcoded?

Because this is a coding challenge, obviously the candidate ID or any sensible data should be hardcoded