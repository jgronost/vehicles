package prv.jgronost.vehicles

class Hello {
    def sayHello(name) {
        println("Hello $name!")
    }
    
    static def main(args) {
        Hello hello = new Hello()
        def name = 'world'
        hello.sayHello(name)
    }

}
