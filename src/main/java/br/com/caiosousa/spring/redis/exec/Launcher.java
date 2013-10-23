package br.com.caiosousa.spring.redis.exec;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.caiosousa.spring.redis.vo.ObjetoCache;

/**
 * 
 * Classe main para executar comandos de teste com o REDIS
 * 
 * @author caiosousa
 *
 */
public class Launcher {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {

		final Logger LOG = Logger.getLogger(Launcher.class);
		
		final ApplicationContext springContext = 
				new AnnotationConfigApplicationContext(new String[]{ "br.com.caiosousa.spring.redis" });

		final RedisTemplate<String, ObjetoCache> redis =
				(RedisTemplate<String, ObjetoCache>) springContext.getBean("redis");
		
		/*
		 * Criação do objeto que será salvo no REDIS
		 */
		ObjetoCache objetoSalvoNoRedis = new ObjetoCache(123L, "Objeto no cache");
		LOG.info("Objeto criado para salvar no REDIS: " + objetoSalvoNoRedis);
		
		/*
		 * Salvar objeto no REDIS
		 */
		redis.opsForValue().set("meuObjeto", objetoSalvoNoRedis);
		
		/*
		 * Recuperar objeto no REDIS
		 */
		objetoSalvoNoRedis = redis.opsForValue().get("meuObjeto");
		LOG.info("Objeto recuperado do REDIS: " + objetoSalvoNoRedis);
		
		/*
		 * Expirar o objeto no cache em 10 segundos
		 */
		LOG.info("Definindo expiração de 10 segundos para o objeto no cache...");
		redis.expire("meuObjeto", 10, TimeUnit.SECONDS);
		
		/*
		 * Recuperar objeto antes de expirar
		 */
		objetoSalvoNoRedis = redis.opsForValue().get("meuObjeto");
		LOG.info("Objeto recuperado do REDIS antes de expirar: " + objetoSalvoNoRedis);
	
		//Esperar 15 segundos
		LOG.info("Aguardar 15 segundos para expirar o objeto no cache...");
		Thread.sleep(15000);
		
		/*
		 * Recuperar objeto depois de expirar
		 */
		objetoSalvoNoRedis = redis.opsForValue().get("meuObjeto");
		LOG.info("Objeto recuperado do REDIS depois de expirar: " + objetoSalvoNoRedis);
		
		/*
		 * Adicionar e recuperar objeto novamente
		 */
		objetoSalvoNoRedis = new ObjetoCache(123L, "Objeto no cache");
		redis.opsForValue().set("meuObjeto", objetoSalvoNoRedis);
		LOG.info("Objeto criado e adicionado ao REDIS: " + objetoSalvoNoRedis);
		
		objetoSalvoNoRedis = redis.opsForValue().get("meuObjeto");
		LOG.info("Objeto recuperado do REDIS: " + objetoSalvoNoRedis);
		
		/*
		 * Exclusão manual de objeto do cache
		 */
		LOG.info("Excluindo manualmente objeto do cache...");
		redis.delete("meuObjeto");
		
		objetoSalvoNoRedis = redis.opsForValue().get("meuObjeto");
		LOG.info("Objeto recuperado depois da exclusão manual do cache: " + objetoSalvoNoRedis);

	}
	
}
