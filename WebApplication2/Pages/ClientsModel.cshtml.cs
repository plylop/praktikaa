using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
namespace WebApplication2.Pages
{
    public class Client
    {
        public int ClientID { get; set; }
        public string FullName { get; set; } // ФИО
        public string Phone { get; set; } // Телефон
        public string Email { get; set; } // Email
    }
    namespace WebApplication2.Pages
    {
        public class ClientsModel : PageModel
        {
            private readonly MyAppDbContext _context;

            public ClientsModel(MyAppDbContext context)
            {
                _context = context;
            }

            public IList<Client> Clients { get; set; } = new List<Client>();
            public Client Client { get; set; }

            public async Task OnGetAsync()
            {
                Clients = await _context.Client.ToListAsync();
            }

            public async Task<IActionResult> OnPostCreateAsync(Client client)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                _context.Client.Add(client);
                await _context.SaveChangesAsync();

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostDeleteAsync(int clientID)
            {
                var client = await _context.Client.FindAsync(clientID);
                if (client != null)
                {
                    _context.Client.Remove(client);
                    await _context.SaveChangesAsync();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnGetEditAsync(int clientID)
            {
                Client = await _context.Client.FindAsync(clientID);
                if (Client == null)
                {
                    return NotFound();
                }
                return Page();
            }

            public async Task<IActionResult> OnPostEditAsync(Client client)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                try
                {
                    _context.Attach(client).State = EntityState.Modified;
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException)
                {
                    ModelState.AddModelError(string.Empty, "Не удалось обновить данные. Попробуйте еще раз.");
                    return Page();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostSearchAsync(string searchTerm)
            {
                if (string.IsNullOrWhiteSpace(searchTerm))
                {
                    Clients = await _context.Client.ToListAsync();
                }
                else
                {
                    Clients = await _context.Client
                        .Where(c => c.FullName.Contains(searchTerm) || c.Phone.Contains(searchTerm))
                        .ToListAsync();
                }

                return Page();
            }
        }
    }
}