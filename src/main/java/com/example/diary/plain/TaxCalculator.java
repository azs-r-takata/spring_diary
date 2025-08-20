package com.example.diary.plain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 税額 = new * rate%を円単位(小数点0桁)で指定のRoundingModeで丸め、
 * 税込 = net + 税額を返す。
 */

public final class TaxCalculator {
	private TaxCalculator() {}
	
	public static BigDecimal addTax(BigDecimal net, int ratePercent, RoundingMode mode) {
		Objects.requireNonNull(net, "net");
		Objects.requireNonNull(mode, "mode");
		if (ratePercent < 0 || ratePercent > 100) {
			throw new IllegalArgumentException("ratePercent must be between 0 and 100");
		}
		
		// 税額 = net * rate / 100 を丸め。movePointLeft(2)で100で割る（中間丸めを発生させない）
		BigDecimal tax = net
				.multiply(BigDecimal.valueOf(ratePercent))
				.movePointLeft(2)
				.setScale(0, mode); //円単位
		
		return net.add(tax);
	}
}
