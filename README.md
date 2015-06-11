<A name="#toc1" title="core.async.zmq.zguide" />
# core.async.zmq.zguide


<A name="#toc2" title="Contents" />
## Contents

**<a href="#toc3">Say what?</a>**

**<a href="#toc4">Let's get it going</a>**

**<a href="#toc5">Fire it up</a>**

**<a href="#toc6">Getting better</a>**

**<a href="#toc7">Ownership and License</a>**

<A name="toc3" title="Say what?" />
### Say what?

Implmentations of the examples from the [ZeroMQ Guide](http://zguide.zeromq.org/page:all).

<A name="toc4" title="Let's get it going" />
### Let's get it going


We need the latest, not yet released version, of core.async built locally

    git clone https://github.com/clojure/core.async.git
    cd core.async
    lein install
    cd ..

and then the build core.async.zmq

    git clone https://github.com/HughPowell/core.async.zmq.git
    cd core.async.zmq
    lein install
    cd ..

<A name="toc5" title="Fire it up" />
### Fire it up

To run any of the examples run the following command

    lein run-*<exmaple-name>*

e.g.

    lein run-hwserver

<A name="toc6" title="Getting better" />
### Getting better

To add a new example
1. Add the .clj file under the src\core\async\zmq\ folder
2. Add a new profile entry to project.clj

    {:main-*<example-name>* {:main core.async.zmq.*<example-name>*}}

3. Add a new alias

    {"run-*<example-name>*" ["with-profile" "main-*<example-name>*" "run"]}

<A name="toc7" title="Ownership and License" />
### Ownership and License

The contributors are listed in AUTHORS. This project uses the MPL v2 license, see LICENSE.

core.async.zmq.zguide uses the [C4.1 (Collective Code Construction Contract)](http://rfc.zeromq.org/spec:22) process for contributions.

core.async.zmq uses the [clojure-style-guide](https://github.com/bbatsov/clojure-style-guide) for code style.

To report an issue, use the [core.async.zmq.zguide issue tracker](https://github.com/HughPowell/core.async.zmq.zguide/issues) at github.com.
