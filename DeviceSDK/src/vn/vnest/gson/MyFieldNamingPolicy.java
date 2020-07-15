package vn.vnest.gson;

import com.google.gson.FieldNamingStrategy;
import java.lang.reflect.Field;

public enum MyFieldNamingPolicy implements FieldNamingStrategy {
	READ_UPPER_CASE() {
		@Override
		public String translateName(Field f) {
			return f.getName();
		}

		@Override
		public String translateReadName(String string) {
			return string.toUpperCase();
		}

	},
	READ_LOWER_CASE() {
		@Override
		public String translateName(Field f) {
			return f.getName();
		}

		@Override
		public String translateReadName(String string) {
			return string.toLowerCase();
		}

	},
	WRITE_LOWER_CASE() {
		@Override
		public String translateName(Field f) {
			return f.getName().toLowerCase();
		}

		@Override
		public String translateReadName(String string) {
			return string;
		}

	},
	ALL_LOWER_CASE() {
		@Override
		public String translateName(Field f) {
			return f.getName().toLowerCase();
		}

		@Override
		public String translateReadName(String string) {
			return string.toLowerCase();
		}

	};
}