package br.edu.ifms.lp3.validator;

public class ClienteValidator {

	private ClienteValidator() {
	}

	public static boolean validaCPF(String cpf) {
		if (cpf == null || cpf.isEmpty() || cpf.equals("00000000000")
				|| cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444")
				|| cpf.equals("55555555555") || cpf.equals("66666666666")
				|| cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11)) {
			return (false);
		}
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;
		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;
		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount))
					.intValue();
			d1 = d1 + (11 - nCount) * digitoCPF;
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		resto = (d1 % 11);
		if (resto < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}
		d2 += 2 * digito1;
		resto = (d2 % 11);
		if (resto < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}
		String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
		return nDigVerific.equals(nDigResult);
	}
}
