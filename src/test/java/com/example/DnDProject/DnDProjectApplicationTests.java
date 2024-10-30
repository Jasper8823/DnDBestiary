package com.example.DnDProject;

import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.DnDProject.Controllers.DatafillController;
import com.example.DnDProject.DTOs.MonsterDTO;
import com.example.DnDProject.Services.DatafillService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.DnDProject.Exceptions.InvalidHPCalculationException;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
class DnDProjectApplicationTests {

	private final DataFetchUtil dataFetchUtil = new DataFetchUtil();
	private final DatafillController controller = new DatafillController();
	@MockBean
	private DatafillService datafillService;

	public void setUp() {
	}

	@Test
	void testCalculateAvgHP_ValidInput() {
		int result = dataFetchUtil.calculateAvgHP(3, 6, 2);
		assertThat(result).isEqualTo(11);
	}

	@Test
	void testCalculateAvgHP_ZeroNumberOfDice() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.calculateAvgHP(0, 6, 2))
				.withMessage("Number of dice must be positive.");
	}

	@Test
	void testCalculateAvgHP_NegativeNumberOfDice() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.calculateAvgHP(-1, 6, 2))
				.withMessage("Number of dice must be positive.");
	}

	@Test
	void testCalculateAvgHP_ZeroDieType() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.calculateAvgHP(3, 0, 2))
				.withMessage("Dice type must be positive.");
	}

	@Test
	void testCalculateAvgHP_NegativeDieType() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.calculateAvgHP(3, -6, 2))
				.withMessage("Die type must be positive.");
	}

	@Test
	void testCalculateAvgHP_NegativePassiveBonus() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.calculateAvgHP(3, 6, -1))
				.withMessage("Passive bonus must not be negative.");
	}

	@Test
	void testFormatHPCalculation_ValidInput() {
		String result = dataFetchUtil.formatHPCalculation(3, 6, 2);
		assertThat(result).isEqualTo("3D6 + 2"); // Expected output
	}

	@Test
	void testFormatHPCalculation_ZeroNumberOfDice() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.formatHPCalculation(0, 6, 2))
				.withMessage("Number of dice must be positive.");
	}

	@Test
	void testFormatHPCalculation_NegativeNumberOfDice() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.formatHPCalculation(-1, 6, 2))
				.withMessage("Number of dice must be positive.");
	}

	@Test
	void testFormatHPCalculation_ZeroDiceType() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.formatHPCalculation(3, 0, 2))
				.withMessage("Dice type must be positive.");
	}

	@Test
	void testFormatHPCalculation_NegativeDiceType() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.formatHPCalculation(3, -6, 2))
				.withMessage("Dice type must be positive.");
	}

	@Test
	void testFormatHPCalculation_NegativePassiveBonus() {
		assertThatExceptionOfType(InvalidHPCalculationException.class)
				.isThrownBy(() -> dataFetchUtil.formatHPCalculation(3, 6, -1))
				.withMessage("Passive bonus must not be negative.");
	}
}


