package com.beust.jcommander;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test ability to validate null strings (useful when the default for an object is the result of a System.getenv(String) call)
 * @author crummy
 */
public class DefaultValueValidationTest {

	static class Config {
		@Parameter(names = "--nullString", validateValueWith = RequiredValidator.class)
		public String nullString = null;
	}

	@Test
	public void testDefaultValue() {
		Config config = new Config();
		Assert.assertThrows(ParameterException.class, () -> new JCommander(config));
	}

	public static class RequiredValidator implements IValueValidator {

		@Override
		public void validate(String name, Object value) throws ParameterException {
			if (value == null) {
				throw new ParameterException(name + " cannot be null, even if it's the default");
			}
		}
	}
}
