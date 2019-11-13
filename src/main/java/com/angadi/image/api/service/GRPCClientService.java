package com.angadi.image.api.service;

import org.springframework.stereotype.Service;

import com.angadi.price.api.grpc.PriceRequest;
import com.angadi.price.api.grpc.PriceResponse;
import com.angadi.price.api.grpc.PriceServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GRPCClientService {
	
	PriceServiceGrpc.PriceServiceBlockingStub blockingStub;

	public void callingGrpcServer(String vendorId,String imageId,float price) {		
		log.info("Starting Client..........................");		
		 ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 5000)
			        .usePlaintext(true)
			        .build();		 
		blockingStub =    PriceServiceGrpc.newBlockingStub(channel);
		
		PriceResponse response = blockingStub.createPrice(
				PriceRequest.newBuilder()
				.setImageId(imageId)
				.setPrice(price)
				.setVendorID(vendorId)
				.build());		
		log.info(response.toString());
		channel.shutdown();
	}

}
