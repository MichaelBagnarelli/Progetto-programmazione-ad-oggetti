package it.univpm.turista.controller;

import java.io.IOException;

import org.json.JSONException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.turista.model.HistoricalResponse;
import it.univpm.turista.model.LiveResponse;

import it.univpm.turista.model.Statistics;

import it.univpm.turista.model.changeCurrency;

@RestController
public class controller {

	@RequestMapping(method = { RequestMethod.GET }, value = "/data", produces = "application/json")
	public String getvalue(@RequestParam String code) throws JSONException, IOException {
		LiveResponse currency = ApiRequest.Liveparsing();
		return currency.getQuotes().toString();

	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/tasso", produces = "aplication/json")
	public String getvaori(@RequestParam String code, String code2) throws JSONException, IOException {
		LiveResponse live = ApiRequest.Liveparsing();
		double ris = changeCurrency.currencyRate(live, code, code2);
		return code + " to " + code2 + ": " + ris;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/perdita", produces = "aplication/json")
	public String getPerdita(@RequestParam String code, String code2, double denaro) throws JSONException, IOException {
		LiveResponse live = ApiRequest.Liveparsing();
		double ris = changeCurrency.perdita(live, code, code2, denaro);
		return "perdità: " + ris;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/stats", produces = "aplication/json")
	public String getStats(@RequestParam String code, String code2, double denaro, String[] date)
			throws JSONException, IOException {
		double[] ris = new double[date.length];
		double[] per = new double[date.length];
		if (date != null) {
			for (int i = 0; i < date.length; i++) {
				HistoricalResponse his = ApiRequest.historicalparsing(date[i]);
				ris[i] = changeCurrency.currencyRate(his, code, code2);

			}
		}
		double variazione = Statistics.variazione(ris);
		String var_percentuale = Statistics.var_percentuale(ris);
		double media = Statistics.avg(ris);
		double varianza = Statistics.varianza(ris);

		if (date != null) {
			for (int i = 0; i < date.length; i++) {
				HistoricalResponse his = ApiRequest.historicalparsing(date[i]);
				double tmp = changeCurrency.perdita(his, code, code2, denaro);
				per[i] = tmp;
			}
		}
		double perditaAssoluta = Statistics.per_ass(per);
		double perditaMedia = Statistics.per_avg(per);

		StringBuilder str = new StringBuilder();
		str.append("Statistiche sulla valuta: \n");
		str.append("variazione: " + variazione + "\n");
		str.append("variazione percentuale: " + var_percentuale + "\n");
		str.append("media: " + media + "\n");
		str.append("varianza: " + varianza + "\n");
		str.append("perdite assulute rispetto a " + code2 + ": " + perditaAssoluta + "\n");
		str.append("perdite media rispetto a " + code2 + ": " + perditaMedia + "\n");

		return str.toString();
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/sportello", produces = "aplication/json")
	public String sceltaSportello(@RequestParam String code, String[] coding) throws IOException {
		LiveResponse live = ApiRequest.Liveparsing();
		String str = changeCurrency.cambiaSportello(live, code, coding);
		return "Sportello con meno perdità: " + str;
	}

}





