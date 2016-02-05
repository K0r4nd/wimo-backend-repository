package com.k0r4nd.wimo.api.mock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.k0r4nd.wimo.api.model.DeliveryStatus;
import com.k0r4nd.wimo.ext.api.model.DhlResponse;
import com.k0r4nd.wimo.ext.api.model.DhlResponse.DhlResponseBuilder;
import com.k0r4nd.wimo.ext.api.model.HermesResponse;
import com.k0r4nd.wimo.ext.api.model.HermesResponse.HermesResponseBuilder;

@RestController
@RequestMapping("/mock")
public class MockController {

	private static Long ONE_DAY_IN_MILLISECONDS = 86400000L;

	private static Long ONE_HOUR_IN_MILLISECONDS = 3600000L;

	private Map<String, DhlResponse> dhlPackages;

	private Map<String, HermesResponse> hermesPackages;

	@RequestMapping("/dhl")
	public DhlResponse getDhlPackageByTrackingId(@RequestParam String trackingId) {
		try {
			if (trackingId.contains("dhl")) {
				Integer number = Integer.parseInt(trackingId.substring(3)) % 4;
				Date now = new Date();
				DhlResponse response = new DhlResponseBuilder().trackingId(trackingId)
						.destinationAddress("MyStreet 1, 1337 Berlin").statusText("Paket ist auf den Weg zu Ihnen!")
						.sentByUser(false).shipperName("DHL").build();
				switch (number) {
				case 0:
					response.setDeliveryState(DeliveryStatus.PASSED_TO_SHIPPER);
					response.setStatusText("Paket wurde elektronisch an DHL übermittelt");
					response.setDeliveryDate(now.getTime() + 2 * ONE_DAY_IN_MILLISECONDS);
					response.setShippingDate(now.getTime());
					response.setLastStatusUpdate(now.getTime());
					break;
				case 1:
					response.setDeliveryState(DeliveryStatus.ON_DELIVERY);
					response.setStatusText("Paket ist auf den Weg");
					response.setDeliveryDate(now.getTime() + ONE_DAY_IN_MILLISECONDS);
					response.setShippingDate(now.getTime() - ONE_DAY_IN_MILLISECONDS);
					response.setLastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS);
					break;
				case 2:
					response.setDeliveryState(DeliveryStatus.DELIVERED);
					response.setStatusText("Paket wurde zugestellt");
					response.setDeliveryDate(now.getTime() - ONE_HOUR_IN_MILLISECONDS);
					response.setShippingDate(now.getTime() - 2 * ONE_DAY_IN_MILLISECONDS);
					response.setLastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS);
					break;
				case 3:
					response.setDeliveryState(DeliveryStatus.CANCELLED);
					response.setStatusText("Paketversandt wurde abgebrochen");
					response.setDeliveryDate(now.getTime() + ONE_DAY_IN_MILLISECONDS);
					response.setShippingDate(now.getTime() - ONE_DAY_IN_MILLISECONDS);
					response.setLastStatusUpdate(now.getTime());
					break;
				}
				return response;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping("/hermes")
	public HermesResponse getHermesByTrackingId(@RequestParam String trackingId) {
		try {
			if (trackingId.contains("her")) {
				Integer number = Integer.parseInt(trackingId.substring(3)) % 4;
				Date now = new Date();
				HermesResponse response = new HermesResponseBuilder().trackingId(trackingId)
						.deliveryState(DeliveryStatus.ON_DELIVERY).destinationAddress("MyStreet 3, 1337 Berlin")
						.statusText("Paket ist auf den Weg!").sentByUser(true).shipperName("HERMES").build();
				switch (number) {
				case 0:
					response.setDeliveryState(DeliveryStatus.PASSED_TO_SHIPPER);
					response.setStatusText("Paket wurde elektronisch an DHL übermittelt");
					response.setDeliveryDate(now.getTime() + 2 * ONE_DAY_IN_MILLISECONDS);
					response.setShippingDate(now.getTime());
					response.setLastStatusUpdate(now.getTime());
					break;
				case 1:
					response.setDeliveryState(DeliveryStatus.ON_DELIVERY);
					response.setStatusText("Paket ist auf den Weg");
					response.setDeliveryDate(now.getTime() + ONE_DAY_IN_MILLISECONDS);
					response.setShippingDate(now.getTime() - ONE_DAY_IN_MILLISECONDS);
					response.setLastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS);
					break;
				case 2:
					response.setDeliveryState(DeliveryStatus.DELIVERED);
					response.setStatusText("Paket wurde zugestellt");
					response.setDeliveryDate(now.getTime() - ONE_HOUR_IN_MILLISECONDS);
					response.setShippingDate(now.getTime() - 2 * ONE_DAY_IN_MILLISECONDS);
					response.setLastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS);
					break;
				case 3:
					response.setDeliveryState(DeliveryStatus.CANCELLED);
					response.setStatusText("Paketversandt wurde abgebrochen");
					response.setDeliveryDate(now.getTime() + ONE_DAY_IN_MILLISECONDS);
					response.setShippingDate(now.getTime() - ONE_DAY_IN_MILLISECONDS);
					response.setLastStatusUpdate(now.getTime());
					break;
				}
				return response;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@PostConstruct
	void initializeData() {
		dhlPackages = new HashMap<String, DhlResponse>();
		Date now = new Date();
		dhlPackages.put("dhl1",
				new DhlResponseBuilder().trackingId("dhl1").deliveryDate(now.getTime() + ONE_DAY_IN_MILLISECONDS)
						.deliveryState(DeliveryStatus.ON_DELIVERY).destinationAddress("MyStreet 1, 1337 Berlin")
						.statusText("Paket ist auf den Weg zu Ihnen!").sentByUser(false)
						.lastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS).shipperName("DHL")
						.shippingDate(now.getTime() - ONE_DAY_IN_MILLISECONDS).build());

		dhlPackages.put("dhl2",
				new DhlResponseBuilder().trackingId("dhl2").deliveryDate(now.getTime() - ONE_DAY_IN_MILLISECONDS)
						.deliveryState(DeliveryStatus.DELIVERED).destinationAddress("MyStreet 2, 1337 Berlin")
						.statusText("Paket wurde zugestellt").sentByUser(false)
						.lastStatusUpdate(now.getTime() - ONE_DAY_IN_MILLISECONDS).shipperName("DHL")
						.shippingDate(now.getTime() - 2 * ONE_DAY_IN_MILLISECONDS).build());

		hermesPackages = new HashMap<String, HermesResponse>();
		hermesPackages.put("hermes1",
				new HermesResponseBuilder().trackingId("hermes1").deliveryDate(now.getTime() + ONE_DAY_IN_MILLISECONDS)
						.deliveryState(DeliveryStatus.ON_DELIVERY).destinationAddress("MyStreet 3, 1337 Berlin")
						.statusText("Paket ist auf den Weg!").sentByUser(true)
						.lastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS).shipperName("HERMES")
						.shippingDate(now.getTime() - ONE_DAY_IN_MILLISECONDS).build());

		hermesPackages.put("hermes2",
				new HermesResponseBuilder().trackingId("hermes2")
						.deliveryDate(now.getTime() + 2 * ONE_DAY_IN_MILLISECONDS)
						.deliveryState(DeliveryStatus.PASSED_TO_SHIPPER).destinationAddress("MyStreet 4, 1337 Berlin")
						.statusText("Paket wurde beim Versender übergeben").sentByUser(false)
						.lastStatusUpdate(now.getTime() - ONE_HOUR_IN_MILLISECONDS).shipperName("HERMES")
						.shippingDate(now.getTime() - ONE_HOUR_IN_MILLISECONDS).build());

	}

}
