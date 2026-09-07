export function mascararCpf(cpf) {
  if (!cpf || !/^\d{11}$/.test(cpf)) {
    throw new Error("CPF inválido");
  }

  const ultimos = cpf.slice(-2);
  return `***.***.***-${ultimos}`;
}