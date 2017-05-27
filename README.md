# FiniteStateMachine
A finite state machine that will work with any provided by you states as long as you will describe a `JSON` file with states presented in a right format.

## Getting Started

This app uses the Gradle build system. 

* To build a project, enter the project directory and use the `./gradlew build` command or use "Import Project" in Android Studio.
* Use `./gradlew test` to run the unit test on your local host.

## File Example

The `JSON` file must have an array of `states`. Each `state` is presented as:

```json
  {
    "name": "AlarmDisarmed_AllUnlocked",
    "transition":
    [
      {"action": "LOCK", "next_state": "AlarmDisarmed_AllLocked"},
      {"action": "LOCKx2", "next_state": "AlarmArmed_AllLocked"},
      {"action": "UNLOCK", "next_state": "AlarmDisarmed_AllUnlocked"},
      {"action": "UNLOCKx2", "next_state": "AlarmDisarmed_AllUnlocked"}
    ]
  },
  ```
  It has a name, and array of `transitions`. Transitions contain an `action` and a `next state`. 

Also there has to be an `initial state`. The state from which the machine starts. It is described like this:

```json
    {
    "initial_state" : "AlarmDisarmed_AllUnlocked"
    },
```

It has only a name. So, if you want to use the FiniteStateMachine class for your automaton, you should replace default values with yours.

## UML diagrams
Lets see the relations between classes and packages
### Package diagram
![Package Diagram](http://1.1m.yt/oVkFnzi.png)
### Class diagram
![Class Diagram](http://1.1m.yt/Igee0Sl.png)

## Launching example
<p align="center">
  <img src="http://3.1m.yt/GvWMesJ.png" width="360" height="640">    <img src="http://3.1m.yt/-ICteQu.png" width="360" height="640">
  </p>
