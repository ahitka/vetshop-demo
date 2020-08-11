package sk.ahitka.vetshop.domain.specification;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import sk.ahitka.vetshop.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProductSpecificationBuilder {

    private final List<SpecSearchCriteria> params = new ArrayList<>();

    public final ProductSpecificationBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }
        Specification<Product> result = new ProductSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate() ? Specification.where(result).or(new ProductSpecification(params.get(i))) : Specification.where(result).and(new ProductSpecification(params.get(i)));
        }
        return result;
    }
}
