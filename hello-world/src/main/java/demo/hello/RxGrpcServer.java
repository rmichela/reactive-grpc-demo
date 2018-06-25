package demo.hello;

import demo.proto.HelloRequest;
import demo.proto.HelloResponse;
import demo.proto.RxGreeterGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class RxGrpcServer extends RxGreeterGrpc.GreeterImplBase {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8888).addService(new GrpcServer()).build().start();
        server.awaitTermination();
    }

    @Override
    public Single<HelloResponse> sayHello(Single<HelloRequest> request) {
        return request
                .map(HelloRequest::getName)
                .map(name -> "Hello " + name)
                .map(greeting -> HelloResponse.newBuilder().setMessage(greeting).build());
    }

    @Override
    public Flowable<HelloResponse> multiGreet(Single<HelloRequest> request) {
        return request
                .map(HelloRequest::getName)
                .flatMapPublisher(name -> Flowable.just(
                        "Welcome " + name,
                        "Hola " + name,
                        "Bonjour " + name))
                .map(greeting -> HelloResponse.newBuilder().setMessage(greeting).build());
    }
}
