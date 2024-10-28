package com.example.DnDProject;

import com.example.DnDProject.Exceptions.InvalidHPCalculationException;
import com.example.DnDProject.UtilMethods.DataFetchUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
class DnDProjectApplicationTests {

	private final DataFetchUtil dataFetchUtil = new DataFetchUtil();


	@Test
	void contextLoads() {

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


