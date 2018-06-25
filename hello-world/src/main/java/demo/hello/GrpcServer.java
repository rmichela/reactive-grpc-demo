package demo.hello;

import demo.proto.GreeterGrpc;
import demo.proto.HelloRequest;
import demo.proto.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcServer extends GreeterGrpc.GreeterImplBase {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8888).addService(new GrpcServer()).build().start();
        server.awaitTermination();
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(HelloResponse.newBuilder().setMessage("Hello " + name).build());
        responseObserver.onCompleted();
    }

    @Override
    public void multiGreet(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(HelloResponse.newBuilder().setMessage("Welcome " + name).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage("Hola " + name).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage("Bonjour " + name).build());
        responseObserver.onCompleted();
    }
}
