package com.parserWikipedia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParserHeader {
	private Document document;
	
	public HTMLParserHeader(Document doc) {
		this.document = doc;
	}
	
	public static void main(String[] args) throws IOException {
				
		FileReader pois = new FileReader("C:\\Users\\Thyelle\\workspace\\Jovens Talentos\\src\\com\\parserWikipedia\\pois-teste.txt");
		BufferedReader buffer = new BufferedReader(pois);
		
		
		String poi = buffer.readLine();
		
		while(poi != null){
			try{
				Document doc = Jsoup.connect("http://www.wikipedia.org/wiki/" + poi).get();
				HTMLParserHeader pag = new HTMLParserHeader(doc); 

				pag.getHeader(poi);
			
			}
			catch(Exception e){
				System.out.println("P�gina n�o encontrada!");
			}
			finally{
				poi = buffer.readLine();
			}
		}
		
		pois.close();
		
	}
	
	public void getHeader(String poi) throws IOException{
		
		File file = new File("C:\\Users\\Thyelle\\workspace\\Jovens Talentos\\src\\com\\parserWikipedia\\" + poi +  ".txt");
		FileWriter poiArq = new FileWriter(file);
		PrintWriter bufferWriter = new PrintWriter(poiArq);
		
		String interestPoint = document.getElementsByClass("firstHeading").text(); //Capturamos o nome do ponto de interesse
		
		bufferWriter.write(interestPoint + "\n");
		System.out.println(interestPoint);
		
		Elements elements = document.getElementsByClass("mw-content-ltr"); // Classe que cont�m todo o texto da p�gina
		
		
		
		for(Element element : elements){
			Elements el = element.getElementsByTag("p"); // Capturamos individualmente cada tag 'p' e fazemos a an�lise (antes de chegar em 'contents',
			for(Element o : el){						 // existe uma ocorr�ncia da tag 'p' onde seu conte�do � vazio). O importante � que, na primeira ocorrencia 
														 //  desse caso de conteudo de tag vazia, todo o conte�do do resumo j� foi lido.
				if(o.getElementsByTag("p").hasText()){    // A an�lise se far� aqui (se n�o existir texto nessa tag, ent�o j� copturamos todo o conte�do do resumo
					String linha = o.getElementsByTag("p").text();
					bufferWriter.write(linha);
					System.out.println(linha);
				}
				else{                                     // c.c finalizamos as an�lises
					bufferWriter.write("\n");
					break;
				}
			}
		}
		
		poiArq.close();
		
		
	}

}