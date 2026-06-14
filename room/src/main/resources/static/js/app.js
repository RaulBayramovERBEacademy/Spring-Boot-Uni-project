document.addEventListener("DOMContentLoaded", () => {
  initListingFilter();
  openModalOnValidationError();
});

function initListingFilter() {
  const filterForm = document.getElementById("filterForm");
  const listingGrid = document.getElementById("listingGrid");
  if (!filterForm || !listingGrid) {
    return;
  }

  const typeSelect = document.getElementById("type");
  const citySelect = document.getElementById("city");
  const maxPriceInput = document.getElementById("maxPrice");
  const resultText = document.getElementById("resultText");
  const noFilterResults = document.getElementById("noFilterResults");
  const cards = listingGrid.querySelectorAll(".listing-card-col");
  const totalCount = cards.length;

  filterForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const selectedType = typeSelect.value;
    const selectedCity = citySelect.value;
    const maxPrice = Number(maxPriceInput.value);
    let visibleCount = 0;

    cards.forEach((card) => {
      const type = card.dataset.type;
      const city = card.dataset.city;
      const price = Number(card.dataset.price);

      const typeOk = selectedType === "all" || type === selectedType;
      const cityOk = selectedCity === "all" || city === selectedCity;
      const priceOk = !maxPrice || price <= maxPrice;
      const show = typeOk && cityOk && priceOk;

      card.classList.toggle("d-none", !show);
      if (show) {
        visibleCount += 1;
      }
    });

    if (resultText) {
      resultText.textContent = `${visibleCount} / ${totalCount}`;
    }

    if (noFilterResults) {
      noFilterResults.classList.toggle("d-none", visibleCount > 0);
    }
  });
}

function openModalOnValidationError() {
  const shouldShow = document.body.dataset.showListingModal === "true";
  if (!shouldShow) {
    return;
  }

  const modalEl = document.getElementById("postListingModal");
  if (modalEl && window.bootstrap) {
    const modal = bootstrap.Modal.getOrCreateInstance(modalEl);
    modal.show();
  }
}
