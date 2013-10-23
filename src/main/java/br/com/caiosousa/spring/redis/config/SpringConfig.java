package br.com.caiosousa.spring.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.caiosousa.spring.redis.vo.ObjetoCache;

/**
 * Configuração dos beans necessários para interagir com o REDIS.
 * 
 * @author caiosousa
 *
 */
@Configuration
public class SpringConfig {

	/**
	 * Criação de connection factory para gerenciar conexões com o REDIS
	 * <b>Nota:</b> Assume-se que o REDIS está instalado e no ar, na porta
	 * padrão (6379).
	 * 
	 * @return JedisConnectionFactory
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		
		final JedisConnectionFactory jedisConnectionFactory = 
				new JedisConnectionFactory();
		jedisConnectionFactory.setUsePool(Boolean.TRUE);
		
		return	jedisConnectionFactory;
				
	}

	/**
	 * Objeto para interagir com o REDIS.
	 * 
	 * @return RedisTemplate
	 */
	@Bean
	public RedisTemplate<String, ObjetoCache> redis() {
		
		final RedisTemplate<String, ObjetoCache> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
		
	}

}
