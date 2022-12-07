export let rules = [{
    name:'is/x/estimation-poker',
    rule:(value, validator) => {
        if (validator.validate('is/number').status === 200) {
            if ([0, 1, 2, 3, 5, 8, 13].some(e => e === value)) {
                return validator.OK('is/x/estimation-poker', value)
            }
        }
        return validator.NOK('is/x/estimation-poker', value)
    }
}]
