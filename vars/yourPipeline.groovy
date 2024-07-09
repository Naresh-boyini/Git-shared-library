import org.example.MyPipeline

def call(Map params = [:]) {
    MyPipeline.runPipeline(params)
}
