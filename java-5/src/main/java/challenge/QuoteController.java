package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/quote")
public class QuoteController {

	@Autowired
	private QuoteService service;;

	@GetMapping
	public ResponseEntity<QuoteDTO> getQuote() {
		try {
			QuoteDTO response = new QuoteDTO(this.service.getQuote().getId(), this.service.getQuote().getActor(),this.service.getQuote().getQuote());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			 return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{actor}")
	public ResponseEntity<QuoteDTO> getQuoteByActor(@PathVariable("actor") String actor) {
		try {
			QuoteDTO response = new QuoteDTO(this.service.getQuoteByActor(actor).getId(), this.service.getQuoteByActor(actor).getActor(),this.service.getQuoteByActor(actor).getQuote());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}
	}

}
