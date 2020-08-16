package br.com.casadocodigo.constraints.validators;

import br.com.casadocodigo.constraints.ValidVoucher;
import br.com.casadocodigo.entities.Voucher;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ValidVoucherValidator implements ConstraintValidator<ValidVoucher, String> {

    private final EntityManager entityManager;

    public ValidVoucherValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean isValid(String voucherCode, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(voucherCode)) return true;
        Query query = entityManager.createQuery("SELECT v FROM Voucher v WHERE v.code = :voucherCode");
        query.setParameter("voucherCode",voucherCode);
        Voucher voucher = (Voucher) query.getSingleResult();

        return Objects.nonNull(voucher) && !voucher.isExpired();
    }

}
