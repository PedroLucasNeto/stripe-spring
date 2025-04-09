listen:
	./stripe listen --foward-to localhost:8080/stripe/webhook

trigger-invoice:
	./stripe trigger invoice.payment_succeeded