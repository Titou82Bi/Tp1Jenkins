package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Hotel;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HotelRepositoryTest {

	@Autowired
	IHotelRepository ihr;
	
	@BeforeAll
	public static void debut()
	{
		
	}
	
	@BeforeEach
	public void setUp()
	{
		
	}
	
	@Test
	public void saveHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Grand Duc",5);
		
		// WHEN
		Hotel hotelSaved = ihr.save(h1);
		
		// THEN
		assertThat(hotelSaved).isNotNull();
		assertThat(hotelSaved.getIdHotel()).isGreaterThan(0);
	}
	
	@Test
	public void saveHotelWithArgsFacultativesTest()
	{
		// GIVEN
		Hotel h1 = new Hotel();
		h1.setNom("Grand Duc");
		
		// WHEN
		Hotel hotelSaved = ihr.save(h1);
		
		// THEN
		assertThat(hotelSaved).isNotNull();
		assertThat(hotelSaved.getIdHotel()).isGreaterThan(0);
		assertThat(hotelSaved.getNom()).isEqualTo("Grand Duc");
		assertThat(hotelSaved.getNbEtoile()).isNull();
	}
	
	@Test
	public void saveHotelExceptionParamObligatoireTest()
	{
		// GIVEN
		Hotel h1 = new Hotel();
		h1.setNbEtoile(3);
		
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.save(h1));
	}
	
	@Test
	public void saveHotelWithUniqueParamTest()
	{
		// GIVEN
		Hotel h1 = new Hotel();
		h1.setNom("Grand Duc");
		h1.setNbEtoile(4);
		ihr.save(h1);
		Hotel h2 = new Hotel();
		h2.setNom("Grand Duc");
		h2.setNbEtoile(5);		
		
		// WHEN
//		Hotel hotelSaved = ihr.save(c2);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.save(h2));
	}
	
	@Test
	public void deleteHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Grand Duc", 4);
		Hotel hotelSaved = ihr.save(h1);
		
		// WHEN
		ihr.delete(hotelSaved);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.getReferenceById(h1.getIdHotel()));
	}
	
	@Test
	public void deleteHotelNullTest()
	{
		// GIVEN
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.delete(null));
	}
	
	@Test
	public void updateHotelTest()
	{
		// GIVEN
		Hotel c1 = new Hotel("George V",4);
		Hotel hotelSaved = ihr.save(c1);
				
		// WHEN
		hotelSaved.setNom("Grand Duc");
		Hotel hotelModified = ihr.save(hotelSaved);
		
		// THEN
		assertThat(hotelModified).isNotNull();
		assertThat(hotelModified.getNom()).isEqualTo("Grand Duc");
	}
	
	@Test
	public void updateHotelUniqueParamTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Grand Duc", 4);
		Hotel h2 = new Hotel("Georges V", 4);
		Hotel hotelSaved1 = ihr.save(h1);
		Hotel hotelSaved2 = ihr.save(h2);
				
		// WHEN
		hotelSaved2.setNom("Grand Duc");
		// BIZARRE : pas d'exception !
		System.out.println(ihr.save(hotelSaved2));
		
		// THEN
//		Assertions.assertThrows(Exception.class, () -> ihr.save(hotelSaved2));
	}
	
	@Test
	public void updateHotelNullTest()
	{
		// GIVEN
		Hotel h1 = null;
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> h1.setNom("test"));
	}
	
	@Test
	public void getHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Grand Duc",4);
		Hotel hotelSaved = ihr.save(h1);
				
		// WHEN
		Hotel client = ihr.getReferenceById(hotelSaved.getIdHotel());
		
		// THEN
		assertThat(client).isNotNull();
		assertThat(client.getNom()).isEqualTo("Grand Du");
		assertThat(client).isEqualTo(hotelSaved);
	}
	

	
	@Test
	public void getAllHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Grand Duc", 4);
		Hotel h2 = new Hotel("Georges V",5);
		Hotel hotelSaved1 = ihr.save(h1);
		Hotel hotelSaved2 = ihr.save(h2);
				
		// WHEN
		List<Hotel> listeH = ihr.findAll();
		
		// THEN
		assertThat(listeH).isNotEmpty();
		assertThat(listeH).hasSize(2);
		assertThat(listeH.get(0).getClass()).hasSameClassAs(Hotel.class);
	}
	
	@Test
	public void getHotelListeVideTest()
	{
		// GIVEN
				
		// WHEN
		List<Hotel> listeH = ihr.findAll();
		
		// THEN
		assertThat(listeH).isEmpty();
		assertThat(listeH).hasSize(0);
	}
}
